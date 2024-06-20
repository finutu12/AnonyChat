using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnonyChat.Model
{
    public class ChatSession
{
       
        public ChatSession(int id)
        {
            this.Id = id;
        }
        public ChatSession() { }

        public int Id { get; set; }
        public string Name { get; set; }
       
    }

}
