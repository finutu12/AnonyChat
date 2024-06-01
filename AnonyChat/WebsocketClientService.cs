using Stomp.Net.Stomp.Protocol;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Reactive.Linq;
using System.Text;
using System.Threading.Tasks;
using WebSocket4Net;

namespace AnonyChat
{
    public class WebSocketClientService
    {

        public async Task Start()
        {

            // Establish WebSocket connection
            var webSocket = new WebSocket("ws://localhost:8080/ws");
            webSocket.Opened += (sender, e) =>
            {
                // Connection opened successfully
                Console.WriteLine("WebSocket connection opened successfully.");

                // Subscribe to STOMP topic
                webSocket.Send("SUBSCRIBE\nid:sub-0\ndestination:/topic/greetings\n\n\x00");
            };

            webSocket.MessageReceived += (sender, e) =>
            {
                // Handle received messages
                Console.WriteLine("Received message: " + e.Message);
            };

            webSocket.Error += (sender, e) =>
            {
                // Handle WebSocket errors
                Console.WriteLine("WebSocket error: " + e.Exception.Message);
            };

            webSocket.Open();

            // Send a test message to the subscribed topic
        }


        public void test()
        {

              //  webSocket.Send("SEND\ndestination:/app/sendMessage\n\n{\"content\":\"Hello from C#\"}\x00");
        }
    }
}
