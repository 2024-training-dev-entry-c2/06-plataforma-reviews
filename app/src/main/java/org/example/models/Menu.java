package org.example.models;

import java.util.LinkedList;
import java.util.List;

public class Menu {
  private Long id;
  private List<Long> dishIds;

  public Menu(Long id) {
    this.id = id;
    this.dishIds = new LinkedList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Long> getDishIds() {
    return dishIds;
  }

  public void setDishIds(List<Long> dishIds) {
    this.dishIds = dishIds;
  }
}
