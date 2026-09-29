package murach.business;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "\"User\"")
public class User implements Serializable {

    @Id
@GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "user_seq"
)
@SequenceGenerator(
    name = "user_seq",
    sequenceName = "\"User_userid_seq\"",
    allocationSize = 1
)
@Column(name = "userid")
private Long userId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    // Tạm thời bỏ password vì bảng User chưa có cột này

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}