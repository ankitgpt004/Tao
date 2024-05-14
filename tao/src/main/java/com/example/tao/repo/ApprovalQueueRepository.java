package com.example.tao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tao.entity.ApprovalQueue;

public interface ApprovalQueueRepository extends JpaRepository<ApprovalQueue, Long>{

}
