package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})

@Entity
public class Article extends AuditingFields {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;

    @Setter @Column(nullable = false) private String title;  // 제목
    @Setter @Column(nullable = false, length = 10000)  private String content; // 본문

    @Setter private String hashtag; // 해시태그

    // 양방향 바인딩
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

//  자동으로 jpa가 세팅해주게 설정할건데 Setter 만들어두면 내가 임의로 이 내용을 수정할 수 있게 되므로 설계 의도를 벗어남.

    protected Article(){}

    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.userAccount = userAccount;

    }
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
