package com.hardik.flash.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.dhatim.fastexcel.Color;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hardik.flash.dto.FileSuccessDto;
import com.hardik.flash.repository.SuperHeroRepository;
import com.hardik.flash.storage.StorageService;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@AllArgsConstructor
public class ExcelService {

	private final SuperHeroRepository superHeroRepository;

	private final StorageService storageService;

	public FileSuccessDto generate() {
		final var superHeroes = superHeroRepository.findAll();
		final var dcSuperHeroes = superHeroes.stream().filter(superHero -> superHero.getCompanyId().equals(1))
				.collect(Collectors.toList());
		final var marvelSuperHeroes = superHeroes.stream().filter(superHero -> superHero.getCompanyId().equals(2))
				.collect(Collectors.toList());
		String awsS3Key = RandomString.make(10);
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Workbook workBook = new Workbook(outputStream, "Super-heroes", "1.0");
			Worksheet workSheetOne = workBook.newWorksheet("all-superheroes");
			Worksheet workSheetTwo = workBook.newWorksheet("super-heroes-detective-comics");
			Worksheet workSheetThree = workBook.newWorksheet("super-heroes-marvel-comics");

			workSheetOne.value(0, 0, "ALL SUPERHEROES");
			workSheetOne.style(0, 0).bold().fillColor(Color.BLACK).fontColor(Color.WHITE).horizontalAlignment("center")
					.set();
			workSheetOne.range(0, 0, 0, 5).style().merge();

			workSheetOne.value(1, 0, "HERO-ID");
			workSheetOne.style(1, 0).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetOne.value(1, 1, "SUPER-HERO-NAME");
			workSheetOne.style(1, 1).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetOne.value(1, 2, "ALTER-EGO");
			workSheetOne.style(1, 2).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetOne.value(1, 3, "COMPANY");
			workSheetOne.style(1, 3).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetOne.value(1, 4, "CREATED-AT");
			workSheetOne.style(1, 4).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetOne.value(1, 5, "UPDATED-AT");
			workSheetOne.style(1, 5).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();

			for (int numberOfRow = 1; numberOfRow <= superHeroes.size(); numberOfRow++) {
				final var superHero = superHeroes.get(numberOfRow - 1);
				int newNumberOfRow = numberOfRow + 1;
				workSheetOne.value(newNumberOfRow, 0, superHero.getId().toString());
				workSheetOne.value(newNumberOfRow, 1, superHero.getName());
				workSheetOne.value(newNumberOfRow, 2, superHero.getAlterEgo());
				workSheetOne.value(newNumberOfRow, 3, superHero.getCompany().getName());
				workSheetOne.value(newNumberOfRow, 4, superHero.getCreatedAt());
				workSheetOne.style(newNumberOfRow, 4).format("yyyy-MM-dd H:mm:ss").set();
				workSheetOne.value(newNumberOfRow, 5, superHero.getUpdatedAt());
				workSheetOne.style(newNumberOfRow, 5).format("yyyy-MM-dd H:mm:ss").set();
			}

			workSheetTwo.value(0, 0, "DETECTIVE COMICS SUPERHEROES");
			workSheetTwo.style(0, 0).bold().fillColor(Color.BLACK).fontColor(Color.WHITE).horizontalAlignment("center")
					.set();
			workSheetTwo.range(0, 0, 0, 5).style().merge();

			workSheetTwo.value(1, 0, "HERO-ID");
			workSheetTwo.style(1, 0).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetTwo.value(1, 1, "SUPER-HERO-NAME");
			workSheetTwo.style(1, 1).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetTwo.value(1, 2, "ALTER-EGO");
			workSheetTwo.style(1, 2).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetTwo.value(1, 3, "COMPANY");
			workSheetTwo.style(1, 3).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetTwo.value(1, 4, "CREATED-AT");
			workSheetTwo.style(1, 4).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetTwo.value(1, 5, "UPDATED-AT");
			workSheetTwo.style(1, 5).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();

			for (int numberOfRow = 1; numberOfRow <= dcSuperHeroes.size(); numberOfRow++) {
				final var superHero = dcSuperHeroes.get(numberOfRow - 1);
				int newNumberOfRow = numberOfRow + 1;
				workSheetTwo.value(newNumberOfRow, 0, superHero.getId().toString());
				workSheetTwo.value(newNumberOfRow, 1, superHero.getName());
				workSheetTwo.value(newNumberOfRow, 2, superHero.getAlterEgo());
				workSheetTwo.value(newNumberOfRow, 3, superHero.getCompany().getName());
				workSheetTwo.value(newNumberOfRow, 4, superHero.getCreatedAt());
				workSheetTwo.style(newNumberOfRow, 4).format("yyyy-MM-dd H:mm:ss").set();
				workSheetTwo.value(newNumberOfRow, 5, superHero.getUpdatedAt());
				workSheetTwo.style(newNumberOfRow, 5).format("yyyy-MM-dd H:mm:ss").set();
			}

			workSheetThree.value(0, 0, "MARVEL COMICS SUPERHEROES");
			workSheetThree.style(0, 0).bold().fillColor(Color.BLACK).fontColor(Color.WHITE)
					.horizontalAlignment("center").set();
			workSheetThree.range(0, 0, 0, 5).style().merge();

			workSheetThree.value(1, 0, "HERO-ID");
			workSheetThree.style(1, 0).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetThree.value(1, 1, "SUPER-HERO-NAME");
			workSheetThree.style(1, 1).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetThree.value(1, 2, "ALTER-EGO");
			workSheetThree.style(1, 2).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetThree.value(1, 3, "COMPANY");
			workSheetThree.style(1, 3).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetThree.value(1, 4, "CREATED-AT");
			workSheetThree.style(1, 4).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();
			workSheetThree.value(1, 5, "UPDATED-AT");
			workSheetThree.style(1, 5).bold().fillColor(Color.LIGHT_TURQUOISE).fontSize(10).set();

			for (int numberOfRow = 1; numberOfRow <= marvelSuperHeroes.size(); numberOfRow++) {
				final var superHero = marvelSuperHeroes.get(numberOfRow - 1);
				int newNumberOfRow = numberOfRow + 1;
				workSheetThree.value(newNumberOfRow, 0, superHero.getId().toString());
				workSheetThree.value(newNumberOfRow, 1, superHero.getName());
				workSheetThree.value(newNumberOfRow, 2, superHero.getAlterEgo());
				workSheetThree.value(newNumberOfRow, 3, superHero.getCompany().getName());
				workSheetThree.value(newNumberOfRow, 4, superHero.getCreatedAt());
				workSheetThree.style(newNumberOfRow, 4).format("yyyy-MM-dd H:mm:ss").set();
				workSheetThree.value(newNumberOfRow, 5, superHero.getUpdatedAt());
				workSheetThree.style(newNumberOfRow, 5).format("yyyy-MM-dd H:mm:ss").set();
			}
			workBook.finish();
			outputStream.flush();
			storageService.save(awsS3Key, new ByteArrayInputStream(outputStream.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FileSuccessDto.builder().status(HttpStatus.OK.value()).key(awsS3Key).timestamp(LocalDateTime.now())
				.build();
	}

}
