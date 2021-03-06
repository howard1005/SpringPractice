package myspring.article;

import webserver.annotations.Autowired;
import webserver.annotations.Primary;
import webserver.annotations.Repository;
import webserver.configures.RowMapper;
import webserver.jdbc.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Primary
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Article article) {
        jdbcTemplate.update(
                "INSERT INTO articles(user_seq,writer,title,content) VALUES (?,?,?,?)",
                article.getUserSeq(),
                article.getWriter(),
                article.getTitle(),
                article.getContent()
        );
    }

    @Override
    public void update(Article article) {
        jdbcTemplate.update(
                "UPDATE articles SET writer=?,title=?,content=? WHERE seq=? AND user_seq=?",
                article.getWriter(),
                article.getTitle(),
                article.getContent(),
                article.getSeq(),
                article.getUserSeq()
        );
    }

    @Override
    public void delete(Article article) {
        jdbcTemplate.update(
                "UPDATE articles SET deleted =? WHERE seq=? AND user_seq=?",
                true,
                article.getSeq(),
                article.getUserSeq()
        );
    }

    @Override
    public Optional<Article> findBySeq(long seq) {
        List<Article> articles = jdbcTemplate.query(
                "SELECT * FROM articles WHERE seq=?",
                mapper,
                seq
        );
        List<Article> validArticles = articles.stream().filter(article -> !article.isDeleted()).collect(Collectors.toList());
        return ofNullable(validArticles.isEmpty() ? null : validArticles.get(0));
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        List<Article> articles = jdbcTemplate.query(
                "SELECT * FROM articles WHERE title=?",
                mapper,
                title
        );
        List<Article> validArticles = articles.stream().filter(article -> !article.isDeleted()).collect(Collectors.toList());
        return ofNullable(validArticles.isEmpty() ? null : validArticles.get(0));
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = jdbcTemplate.query(
                "SELECT * FROM articles  WHERE deleted=false ORDER BY seq DESC",
                mapper
        );
        return articles.stream().filter(article -> !article.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public int CountAll() {
        return jdbcTemplate.queryForObject(
                "SELECT count(*) FROM articles  WHERE deleted=false",
                int.class
        );
    }

    static RowMapper<Article> mapper = (rs, rowNum) ->
            ArticleFactory.createArticle(
                    rs.getLong("seq"),
                    rs.getLong("user_seq"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("time").toLocalDateTime(),
                    rs.getInt("reply_count"),
                    rs.getBoolean("deleted")
            );

}
