package dto;

import lombok.*;

import java.util.Date;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor@Builder
public class UserFull {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String phone;
    private Location location;
    private String registerDate;
    private String updatedDate;
}
@Setter@Getter@NoArgsConstructor@AllArgsConstructor
 class Location{
    public String street;
    public String city;
    public String state;
    public String country;
    public String timezone;
}
