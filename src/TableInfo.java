public class TableInfo {
    private String tableName;
    private int columnCount;
    private int rowCount;
    public TableInfo(String tableName, int columnCount, int rowCount) {
        this.tableName = tableName;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public int getColumnCount() {
        return columnCount;
    }
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
    public int getRowCount() {
        return rowCount;
    }
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    @Override
    public String toString() {
        return tableName + ": "+columnCount+
                " Spalten und "  +
                rowCount + " Zahlen";
    }
}

