package kz.beibarys.spring.voenkaProject.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Images extends BaseEntity{
    @Column(name="name")
    private String fileName;

    @Column(name="content")
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

    @Column(name = "image")
    @Transient
    private MultipartFile image;
}
