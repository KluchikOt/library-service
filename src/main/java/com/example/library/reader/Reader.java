package com.example.library.reader;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate registrationDate;



    public Reader( String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Reader)) return false;
        if(getId() == null) return false;
        Reader reader = (Reader) o;
        return Objects.equals(getId(), reader.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    public void updateData(String newLastName, String newEmail, String newPhoneNumber) {
        this.lastName = newLastName;
        this.email = newEmail;
        this.phoneNumber = newPhoneNumber;
    }
}
