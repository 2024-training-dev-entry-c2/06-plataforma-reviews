package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class Menu {
  private Long id;
  private Set<Long> dishIds;

  public Menu(Long id) {
    this.id = id;
    this.dishIds = new HashSet<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<Long> getDishIds() {
    return dishIds;
  }

}
