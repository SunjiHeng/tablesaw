package com.deathrayresearch.outlier;

import com.deathrayresearch.outlier.columns.Column;
import com.deathrayresearch.outlier.columns.ColumnMetadata;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TableMetadata {

  static final Gson GSON = new Gson();

  private final String id;

  private final String name;

  private final int rowCount;

  private final List<ColumnMetadata> columnMetadataList = new ArrayList<>();

  public TableMetadata(Table table) {
    this.id = table.id();
    this.name = table.name();
    this.rowCount = table.rowCount();
    for (Column column : table.columns()) {
      columnMetadataList.add(new ColumnMetadata(column));
    }
  }

  public String toJson() {
    return GSON.toJson(this);
  }

  public static TableMetadata fromJson(String jsonString) {
    return GSON.fromJson(jsonString, TableMetadata.class);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TableMetadata that = (TableMetadata) o;

    if (rowCount != that.rowCount) return false;
    if (!id.equals(that.id)) return false;
    if (!name.equals(that.name)) return false;
    return columnMetadataList.equals(that.columnMetadataList);

  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + rowCount;
    result = 31 * result + columnMetadataList.hashCode();
    return result;
  }
}
