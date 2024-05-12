package org.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[chat_session]")
public class ChatSession {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "chat_id")
    private Integer id;

    @Column(name = "name")
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy="chatSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="chatSession", fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

//    private List<Message> messages;
    // Other session attributes

    // Constructors, getters, and setters
    public ChatSession(String name){
        this.name = name;
    }
}
