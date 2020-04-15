package kz.beibarys.spring.voenkaProject.Entities;

import kz.beibarys.spring.voenkaProject.Controllers.BaseController;
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
@Table(name = "documents")
public class Documents extends BaseEntity {
    @Column(name="name")
    private String fileName;

    @Column(name="content")
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

    @Column(name = "document")
    @Transient
    private MultipartFile document;
}
