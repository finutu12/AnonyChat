using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnonyChat
{
    public static class StompHelper
    {
        public static string SubscribeFrame(string destination, string id)
        {
            return $"SUBSCRIBE\nid:{id}\ndestination:{destination}\n\n\0";
        }

        public static string ConnectFrame()
        {
            return $"CONNECT\n\n\0";
        }

        public static string DisconnectFrame()
        {
            return "DISCONNECT\n\n\0";
        }
    }
}
