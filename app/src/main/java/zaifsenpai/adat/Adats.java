package zaifsenpai.adat;

import java.util.ArrayList;
import java.util.List;

public class Adats {
    List<String> Names;
    List<Integer> Count;
    List<String> Backgrounds;

    public List<String> getNames() {
        return Names;
    }

    public void setNames(List<String> names) {
        Names = names;
    }

    public List<Integer> getCount() {
        return Count;
    }

    public void setCount(List<Integer> count) {
        Count = count;
    }

    public Adats() {
        Names = new ArrayList<>();
        Count = new ArrayList<>();
        Backgrounds = new ArrayList<>();
    }

    public List<String> getBackgrounds() {
        return Backgrounds;
    }

    public void setBackgrounds(List<String> backgrounds) {
        Backgrounds = backgrounds;
    }

    public Adats(List<String> names, List<Integer> count, List<String> backgrounds) {
        Names = names;
        Count = count;
        Backgrounds = backgrounds;
    }
}
