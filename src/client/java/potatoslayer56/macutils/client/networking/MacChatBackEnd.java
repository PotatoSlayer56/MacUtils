package potatoslayer56.macutils.client.networking;

import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import potatoslayer56.macutils.MacUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MacChatBackEnd {
  public static MqttClient mqttClient;
  public static Map<String,String> message = new HashMap<>();

  private static String topic = "MacChat";
  private static String broker = "tcp://broker.emqx.io:1883";
  private static int qos = 2;

  private static void sendChat(Map<String, String> message) {
    Minecraft.getInstance().gui.getChat().addClientSystemMessage(Component.translatable("prefix.macutils.macchat").append("%s: ".formatted(message.get("name"))).append(message.get("content")));
  }

  public static void connectToMqtt(){
    try {
      mqttClient = new MqttClient(broker, MqttClient.generateClientId());
      MqttConnectOptions connectOptions = new MqttConnectOptions();
      connectOptions.setCleanSession(true);
      connectOptions.setKeepAliveInterval(30);
      connectOptions.setAutomaticReconnect(true);
      connectOptions.setConnectionTimeout(60);
      mqttClient.connect(connectOptions);
      mqttClient.setCallback(new MqttCallback() {
        @Override
        public void connectionLost(Throwable throwable) {

        }

        @Override
        public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
          receiveMqttMessage(mqttMessage);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        }
      });
      mqttClient.subscribe(topic, qos);
      MacUtils.LOGGER.info("Connected to MQTT");
    } catch (MqttException e) {
      MacUtils.LOGGER.error(e.toString());
    }
  }

  public static void sendMqttMessage(String text){
    if(Minecraft.getInstance().player != null){
      Gson gson = new Gson();
      message.put("name", Minecraft.getInstance().player.getName().getString());
      message.put("content", text);
      MqttMessage mqttMessage = new MqttMessage(gson.toJson(message).getBytes(StandardCharsets.UTF_8));
      mqttMessage.setQos(qos);
      try {
        mqttClient.publish(topic, mqttMessage);
      } catch (MqttException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void receiveMqttMessage(MqttMessage mqttMessage){
    if(Minecraft.getInstance().player != null) {
      Gson gson = new Gson();
      Map<String, String> receivedMessage = gson.fromJson(new String(mqttMessage.getPayload(), StandardCharsets.UTF_8), HashMap.class);
      MacUtils.LOGGER.info(receivedMessage.get("content"));
      sendChat(receivedMessage);
    }
  }
}
