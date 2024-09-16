package com.RP;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReactiveProgramingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void workingWithMono() throws InterruptedException {

		// Mono -> publisher have 0 to 1 elements
		//created mono

//
//		Mono<String> error= Mono.error(new RuntimeException());
//		Mono<String> mono =
//				Mono.just("Mono example")
//						.log()
//						.then(error);
//
//
//		//subscribed mono
//		mono.subscribe(data ->{
//			System.out.println("data is :"+data);
//		});
//
//		error.subscribe(System.out::println);


		Mono<String> mono1 = Mono.just("First mono! with reactive programing");
		Mono<String> mono2 = Mono.just("second mono!");
		Mono<Integer> mono3 = Mono.just(1234);

		//using map to transform mono

		Mono<String> resultMono = mono1.map(Item -> Item.toUpperCase());
		resultMono.subscribe(data ->{
			System.out.println(data);
		});

		Mono<String[]> flatMapMono = mono1.flatMap(value -> Mono.just(value.split(" ")));

		flatMapMono.subscribe(data ->{
			for (String s:data){
				System.out.println(s);
			}
		});
		System.out.println("========================================");
		Flux<String> flatMapManyResult = mono1.flatMapMany(value -> Flux.just(value.split(" "))).log();
		flatMapManyResult.subscribe(data->{
			System.out.println(data);
		});
		System.out.println("========================================");

		System.out.println(Thread.currentThread().getName());

		Flux<String> stringFlux = mono1.concatWith(mono2).log()
				.delayElements(Duration.ofMillis(2000));
		stringFlux.subscribe(data->{
			System.out.println(Thread.currentThread().getName());
			System.out.println(data);
		});

		Thread.sleep(3000);//the delay moves to another thred scheduler and run while main keep running to avoid this
		//make main sleep too to get the result on main.

		Mono<Tuple3<String, String, Integer>> zippedMono = Mono.zip(mono1, mono2, mono3);

		zippedMono.subscribe(data ->{
			System.out.println(data);
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
		});

		Mono<Tuple2<String, String>> zipMono = mono1.zipWith(mono2);

		zipMono.subscribe(data ->{
			System.out.println(data);
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});

		Mono<List<String>> monoString=Mono.just( List.of("hello","My","name","is","deepak"));

		List<String> sample=new ArrayList<>();
		monoString.subscribe(data->{
			for (String s:data){
				sample.add(s);

			}
		});

		for (String s:sample){
			System.out.println(s);
		}




	}

}
