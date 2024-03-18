package ru.hogwarts.school;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;


 /*   public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            return !(o == null || !(o instanceof User) || this.id != ((User) o).getId() || !this.name.equals((User) o).getName());
    }*/

}