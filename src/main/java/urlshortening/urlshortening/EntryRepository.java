package urlshortening.urlshortening;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Integer> {
    // Query to find the origional URL with short URL
    @Query(value = "SELECT original_url FROM entry WHERE short_url=?1", nativeQuery = true)
    String findByShortUrl(String shortUrl);

    // Verify if the short URL exists in the database
    boolean existsByShortUrl(String shortURL);

    // Query to retrieve the short URL cretaed timestamp
    @Query(value = "SELECT create_at FROM entry WHERE short_url=?1", nativeQuery = true)
    LocalDateTime findTimestamp(String shortUrl);
}
