package it.pc.test.WebSpringApp.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
public class TokenDTO {

  private String token;
  private LocalDateTime expiresDate;

  public void setExpiresDate(Date expiresDate) {
    if (expiresDate != null) {
      this.expiresDate = expiresDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
  }

  /*  public LocalDateTime getExpireDate() {
    return LocalDateTime.now().plus(expiresIn, ChronoUnit.MILLIS);
  }*/
}
