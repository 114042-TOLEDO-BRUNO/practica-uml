insert into cars_type(type,price)
values('AUTO',10000);
insert into cars_type(type,price)
values('MOTO',5000);
insert into cars_type(type,price)
values('CAMIONETA',15000);
insert into cars(car_type_id,brand,model)
values(1,'FIAT','VIVACE');
insert into cars(car_type_id,brand,model)
values(1,'FIAT','SIENA');
insert into cars(car_type_id,brand,model)
values(1,'FIAT','CRONOS');
insert into cars(car_type_id,brand,model)
values(1,'FIAT','ARGOS');
insert into cars(car_type_id,brand,model)
values(1,'VOLSWAGEN','TREND');
insert into cars(car_type_id,brand,model)
values(1,'VOLSWAGEN','POLO');
insert into cars(car_type_id,brand,model)
values(3,'VOLSWAGEN','T-CROSS');
insert into cars(car_type_id,brand,model)
values(3,'VOLSWAGEN','NIVUS');
insert into cars(car_type_id,brand,model)
values(3,'FORD','RAPTOR');
insert into cars(car_type_id,brand,model)
values(3,'FORD','RANGER');
insert into cars(car_type_id,brand,model)
values(1,'FORD','ECOSPORT');
insert into cars(car_type_id,brand,model)
values(2,'HONDA','WAVE');
insert into cars(car_type_id,brand,model)
values(2,'HONDA','CBR');
insert into cars(car_type_id,brand,model)
values(2,'HONDA','VARADERO');
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('BRUNO',1,1,'2023-08-05','2023-08-06',50000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('BRUNO',2,1,'2023-08-05','2023-08-06',50000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('BRUNO',3,1,'2023-08-05','2023-08-06',50000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('BRUNO',4,1,'2023-08-05','2023-08-06',50000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('BRUNO',5,1,'2023-08-05','2023-08-06',50000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('NICOLAS',8,3,'2023-08-06','2023-08-07',30000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('PELUSA',12,2,'2023-08-07','2023-08-09',100000);
insert into rentals(customer,car_id,rented_days,start_rent,end_rent,total_price)
values('PELUSA',14,2,'2023-08-06','2023-08-10',100000);