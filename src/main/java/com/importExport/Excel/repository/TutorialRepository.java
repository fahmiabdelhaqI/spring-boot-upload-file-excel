package com.importExport.Excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.importExport.Excel.model.Tutorial;

@Repository
public interface TutorialRepository  extends JpaRepository<Tutorial, Long>{

}
