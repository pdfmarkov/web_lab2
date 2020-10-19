package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table implements Serializable {

    private List<Cell> result;

    public Table() {
        result = new ArrayList<>();
    }

    public void addCell(Cell cell) {
        result.add(cell);
    }

    public List<Cell> getResult() {
        return result;
    }

    public void setResult(List<Cell> result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table o1 = (Table) o;
        return Objects.equals(result, o1.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return "Table{" +
                "result=" + result +
                "}";
    }

}
