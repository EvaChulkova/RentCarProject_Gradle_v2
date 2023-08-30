package jane.rentcarproject_gradle.service;

/*@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:/home/janechulkova/DMDev/RentCarProject_Gradle/images}")
    private final String bucket;

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path fullImagePath = Path.of(bucket, imagePath);

        try (content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }
}*/
