
package urlshortening.urlshortening;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.codec.digest.DigestUtils;

@RestController
public class UrlController {
    @Autowired
    private EntryRepository entryRepository;

    // Handles GET requests to retrieve the original URL based on a short URL
    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalURL(@PathVariable String shortUrl, HttpServletResponse response) {
        String originalLink = entryRepository.findByShortUrl(shortUrl);
        LocalDateTime entryDateTime = entryRepository.findTimestamp(shortUrl);
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (originalLink != null) {
            Duration time = Duration.between(entryDateTime, currentDateTime);
            if (time.toSeconds() < 180) {
                try {
                    if (!originalLink.startsWith("http://") && !originalLink.startsWith("https://")) {
                        originalLink = "https://" + originalLink;
                    }
                    response.sendRedirect(originalLink);
                    return ResponseEntity.ok().build();
                } catch (IOException e) {
                    // Handle the exception
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to redirect to " + originalLink + " URL");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("shyamala:8080/" + shortUrl + "  URL time Expired");

            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Handles POST requests to genrate hashed short URL for the original URL, store
    // the data in the DB and return the generated hashed short URL
    @PostMapping("/link")
    public @ResponseBody String addNewEntry(@RequestParam final String input) {
        Entry n = new Entry();
        String link = input;
        String nonalpha_link = link.replaceAll("[^\\p{Alnum}]", "");
        String shortURL = DigestUtils.sha256Hex(nonalpha_link);
        int shotURL_len = 6;

        // Ensure the shortURL is of the desired length
        if (shortURL.length() > shotURL_len) {
            shortURL = shortURL.substring(0, 6);
        } else if (shortURL.length() < shotURL_len) {
            StringBuilder sb = new StringBuilder(shortURL);
            while (sb.length() < shotURL_len) {
                sb.append(sb.toString());
            }
            shortURL = sb.substring(0, shotURL_len);
        }

        if (entryRepository.existsByShortUrl(shortURL) == false || entryRepository.findAll() == null) {

            LocalDateTime timestamp = LocalDateTime.now();

            n.setOriginalUrl(input);
            n.setshortUrl(shortURL);
            n.setcreateAt(timestamp);
            entryRepository.save(n);
        }

        return "shyamala:8080/" + shortURL;
    }
}
