package com.example.SlangWord;

public class SlangWord {
    private String slang;
    private String definition;

    public SlangWord(String slang, String definition) {
        this.slang = slang;
        this.definition = definition;
    }

    public SlangWord(String str) {
        String[] arr = str.split("`");
        this.slang = arr[0];
        this.definition = arr[1];
    }

    public String getSlang() {
        return slang;
    }

    public void setSlang(String slang) {
        this.slang = slang;
    }

    public String getDefinition() {
        return definition;
    }

    public String getSplittedDefinition() {
        String[] arr = definition.split("\\|");
        String str = "";

        for (String item : arr) {
            str = str + item.strip() + "\n";
        }

        return str;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "SlangWord{" +
                "slang='" + slang + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }

    public String toCompactString() {
        return slang + "`" + definition;
    }
}
