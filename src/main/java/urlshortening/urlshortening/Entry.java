package urlshortening.urlshortening;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Entry {

    @Column(name = "originalUrl", columnDefinition = "LONGTEXT")
    private String originalUrl;

    @Id
    @Column(unique = true, nullable = false)
    private String shortUrl;

    private LocalDateTime createAt;

    public String getoriginalUrl(int id) {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getshortUrl() {
        return shortUrl;
    }

    public void setshortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getcreateAt() {
        return createAt;
    }

    public void setcreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

}
