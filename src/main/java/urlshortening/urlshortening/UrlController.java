
package urlshortening.urlshortening;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class UrlController {
    @Autowired
    private EntryRepository entryRepository;

    public EntryRepository getUserRepository() {

        return entryRepository;
    }

    public void setUserRepository(EntryRepository userRepository) {
        this.entryRepository = userRepository;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalURL(@PathVariable String shortUrl, HttpServletResponse response) {

        String originalLink = entryRepository.findByShortUrl(shortUrl);
        try {
            response.sendRedirect(originalLink);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/link")
    public @ResponseBody String addNewEntry(@RequestParam String link) {
        Entry n = new Entry();
        String originalUrl = link;
        String nonalpha_link = link.replaceAll("[^\\p{Alnum}]", "");
        long id = nonalpha_link.hashCode();
        if (entryRepository.findById(id) != id) {
            LocalDateTime timestamp = LocalDateTime.now();
            String ranChar = "abcdefghijklmnopqrstuvwxyzABACDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            String shortURL = "";
            int len = 5;
            while (len > 0) {
                shortURL += ranChar.charAt((int) (Math.random() * ranChar.length()));
                len--;
            }

            n.setId(id);
            n.setOriginalUrl(originalUrl);
            n.setshortUrl(shortURL);
            n.setcreateAt(timestamp);
            entryRepository.save(n);

            return "shyamala:8080/" + shortURL;

        }

        else {
            return "shyamala:8080/" + entryRepository.findShortUrl(id);
        }

    }

}
