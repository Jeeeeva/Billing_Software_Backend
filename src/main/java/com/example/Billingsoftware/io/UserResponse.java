package com.example.Billingsoftware.io;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private String name;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String role;
}
