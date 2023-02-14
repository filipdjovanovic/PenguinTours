package com.penguins.project.controller.Arrangement;

import com.penguins.project.controller.Program.ProgramW;
import com.penguins.project.model.Arrangement.Arrangement;

import com.penguins.project.model.Picture.Picture;
import com.penguins.project.model.Picture.PictureParam;
import com.penguins.project.model.Picture.PictureRepository;
import com.penguins.project.model.Picture.PictureService;
import com.penguins.project.service.ArrangementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;


@RequestMapping(path = "/arrangements")
@RestController
@CrossOrigin("http://localhost:3000")
public class ArrangementController {
    private final ArrangementService arrangementService;
    private final PictureService pictureService;

    private final PictureRepository pictureRepository;

    public ArrangementController(ArrangementService arrangementService, PictureService pictureService, PictureRepository pictureRepository) {
        this.arrangementService = arrangementService;
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
    }

    @PostMapping(path = "/add")
    public void addArrangement(@RequestBody ArrangementParam arrangementParam){
        Arrangement arrangement = arrangementParam.toArrangement();
        arrangementService.addArrangement(arrangement);

    }

    @GetMapping(value = "/all")
    public Page<ArrangementShortW> getAllArrangements(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {

        if (page == null) page = 0;
        if (size == null) size = 50;

        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.getAllArrangements(pageable);
    }


    @GetMapping(value = "/get", params = {"id"})
    @ResponseBody
    public ArrangementW getArrangementById(@RequestParam Long id) {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        if (arrangement == null){
            throw new IllegalStateException("Arrangement with id " + id + " does not exists");
        }
        ArrangementW arrangementW = new ArrangementW(arrangement);
        List<ProgramW> programs = arrangementW.getPrograms();
        programs.sort(Comparator.comparing(ProgramW::getDate));

        arrangementW.setPrograms(programs);
        return arrangementW;

    }

    @PutMapping(value = "/update", params={"id"})
    public void updateArrangement(@RequestParam Long id, @RequestBody ArrangementParam arrangementParam){
        System.out.println(arrangementParam);
        arrangementService.updateArrangement(id,arrangementParam);
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public Page<ArrangementShortW> getArrangements(@RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String city,
                                                   @RequestParam(required = false) String country,
                                                   @RequestParam(required = false) String continent,
                                                   @RequestParam(required = false) String transportation,
                                                   @RequestParam(required = false) Date startDate,
                                                   @RequestParam(required = false) Date endDate){


        if (page == null) page = 0;
        if (size == null) size = 50;
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.getArrangements(name,city,country,continent,transportation,startDate,endDate,pageable);

    }

    @DeleteMapping(value = "/delete", params={"id"})
    public void deleteArrangement(@RequestParam Long id){
        arrangementService.deleteArrangement(id);
    }

    @GetMapping(value = "/hot")
    public List<ArrangementShortW> getTopArrangements() {
        return arrangementService.getTopArrangements();
    }

    /*
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@ModelAttribute MultipartFile image) throws IOException {
        pictureService.uploadImage(image.getBytes());
        return ResponseEntity.ok().body("Image successfully uploaded!");
    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        // Retrieve the image data from a database or the file system, based on the id passed in the URL.
        byte[] imageData = pictureService.getImage(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

     */
    @PostMapping("/image/upload")
    public ResponseEntity<String> saveImage(@RequestBody PictureParam pictureParam) {

        System.out.println(pictureParam.getName());
        System.out.println(pictureParam.getMyFile().substring(23,30));
        // Decode the Base64 encoded image string
        byte[] imageBytes = Base64.getDecoder().decode(pictureParam.getMyFile().substring(23));

        // Save the image to a local file
        try (FileOutputStream fos = new FileOutputStream("images/image.jpeg")) {
            fos.write(imageBytes);
            return new ResponseEntity<>("Image saved successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error saving image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/image/get")
    public PictureParam getImage() throws IOException {
        // Read the image from the file system
        Path path = Paths.get("images/image.jpeg");
        byte[] imageBytes;

        imageBytes = Files.readAllBytes(path);

        // Encode the image as a Base64 string
        String image = Base64.getEncoder().encodeToString(imageBytes);

        PictureParam pictureParam = new PictureParam();
        pictureParam.setName("Ivaneee");
        pictureParam.setMyFile(image);

        return pictureParam;
    }
    /*
    @PostMapping("image/upload")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        try {
            // Save the file to the file system
            String fileName = file.getOriginalFilename();
            String filePath = "images/" + fileName;
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            pictureService.uploadImage(filePath);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        Picture picture = pictureRepository.findById(id).orElse(null);
        if (picture == null) {
            return ResponseEntity.notFound().build();
        }


        byte[] imageBytes = Files.readAllBytes(Paths.get(picture.getFilePath()));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

    }

     */
}
