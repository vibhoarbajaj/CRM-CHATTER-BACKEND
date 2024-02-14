package exception.person;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PersonApiResponse {
private String msg;
private Boolean success;
}
