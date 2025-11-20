package Hackerrank.codingapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
   private Long id;
   private String title;

   @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL)
   private List<Enrollment> enrollments = new ArrayList<>();
}
