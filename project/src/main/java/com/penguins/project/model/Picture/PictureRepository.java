package com.penguins.project.model.Picture;

import com.penguins.project.model.Location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository  extends JpaRepository<Picture, Long> {
}
