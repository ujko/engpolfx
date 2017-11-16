package engpol.db.model;

import javax.persistence.*;

@Entity
@Table(name = "engpol")
public class Engpol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "eng_word")
    private String engWord;

    @Column(name = "pol_word")
    private String polWord;

    @Column(name = "eng_sentence")
    private String engSentence;

    @Column(name = "pol_sentence")
    private String polSentence;

    public Engpol() {
    }
    public Engpol(EngpolBuilder builder) {
        this.id = builder.id;
        this.engWord = builder.engWord;
        this.polWord = builder.polWord;
        this.engSentence = builder.engSentence;
        this.polSentence = builder.polSentence;

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEngWord() {
        return engWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }

    public String getPolWord() {
        return polWord;
    }

    public void setPolWord(String polWord) {
        this.polWord = polWord;
    }

    public String getEngSentence() {
        return engSentence;
    }

    public void setEngSentence(String engSentence) {
        this.engSentence = engSentence;
    }

    public String getPolSentence() {
        return polSentence;
    }

    public void setPolSentence(String polSentence) {
        this.polSentence = polSentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engpol engpol = (Engpol) o;

        if (!engWord.equals(engpol.engWord)) return false;
        return polWord.equals(engpol.polWord);
    }

    @Override
    public int hashCode() {
        int result = engWord.hashCode();
        result = 31 * result + polWord.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Engpol{" +
                "id=" + id +
                ", engWord='" + engWord + '\'' +
                ", polWord='" + polWord + '\'' +
                ", engSentence='" + engSentence + '\'' +
                ", polSentence='" + polSentence + '\'' +
                '}';
    }

    public static class EngpolBuilder {
        private long id;
        private String engWord;
        private String polWord;
        private String engSentence;
        private String polSentence;

        public EngpolBuilder id(long id) {
            this.id = id;
            return this;
        }

        public EngpolBuilder engWord(String engWord) {
            this.engWord = engWord;
            return this;
        }

        public EngpolBuilder polWord(String polWord) {
            this.polWord = polWord;
            return this;
        }

        public EngpolBuilder engSentence (String engSentence) {
            this.engSentence = engSentence;
            return this;
        }

        public EngpolBuilder polSentence (String polSentence) {
            this.polSentence = polSentence;
            return this;
        }

        public Engpol build() throws IllegalArgumentException {
            if(engWord==null || "".equals(engWord)){
                throw new IllegalArgumentException("Brak angielskiego słowa");
            }
            if(polWord==null || "".equals(polWord)){
                throw new IllegalArgumentException("Brak polskiego słowa");
            }
            return new Engpol(this);
        }
    }
}

