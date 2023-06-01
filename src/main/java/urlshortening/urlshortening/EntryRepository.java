
package urlshortening.urlshortening;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Integer> {
    @Query(value = "SELECT original_url FROM entry WHERE short_url=?1", nativeQuery = true)
    String findByShortUrl(String shortUrl);

    @Query(value = "SELECT id FROM entry WHERE id=?1", nativeQuery = true)
    long findById(long id);

    @Query(value = "SELECT short_url FROM entry WHERE id=?1", nativeQuery = true)
    String findShortUrl(long id);
}
