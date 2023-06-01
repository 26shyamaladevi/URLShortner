package urlshortening.urlshortening;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Entry {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String originalUrl;

    private String shortUrl;

    private LocalDateTime createAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }

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
