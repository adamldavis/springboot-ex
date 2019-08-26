package java11webflux.model;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Segment {

    //String video;

    UUID id = UUID.randomUUID();

    String name;

    String body; // body of the course as text

    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;
        Segment segment = (Segment) o;
        return Objects.equals(id, segment.id);
    }
}
