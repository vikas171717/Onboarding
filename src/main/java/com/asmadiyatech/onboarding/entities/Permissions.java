<<<<<<< HEAD
package com.asmadiyatech.onboarding.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String name ; 
}
=======
package com.asmadiyatech.onboarding.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String name ; 
}
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
