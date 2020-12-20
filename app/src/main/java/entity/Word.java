package entity;

public class Word {
    private String id;
    private String langFirst;
    private String langSecond;

    public Word() {
    }

    public Word(String id, String langFirst, String langSecond) {
        this.id = id;
        this.langFirst = langFirst;
        this.langSecond = langSecond;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLangFirst() {
        return langFirst;
    }

    public void setLangFirst(String langFirst) {
        this.langFirst = langFirst;
    }

    public String getLangSecond() {
        return langSecond;
    }

    public void setLangSecond(String langSecond) {
        this.langSecond = langSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!id.equals(word.id)) return false;
        if (!langFirst.equals(word.langFirst)) return false;
        return langSecond.equals(word.langSecond);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + langFirst.hashCode();
        result = 31 * result + langSecond.hashCode();
        return result;
    }
}
