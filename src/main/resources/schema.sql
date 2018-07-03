create table Answers (answerIdk bigint, answerNo bigint, answerYes bigint, question_id bigint not null, gift_id bigint not null, primary key (gift_id, question_id)); /;
create table Gift (id bigint not null, description varchar(255), likes bigint, name varchar(255) not null, url varchar(255) not null, primary key (id)); /;
create table Gift_Answers (Gift_id bigint not null, answers_gift_id bigint not null, answers_question_id bigint not null); /;
create table Question (id bigint not null, question varchar(255) not null, primary key (id)); /;
create table Question_Answers (Question_id bigint not null, answers_gift_id bigint not null, answers_question_id bigint not null); /;
alter table Gift add constraint UK_q5rc85i8y40xhd5wb0iqqbj4w unique (url); /;
alter table Gift_Answers add constraint UK_agyvi9ppomjt9ghgnuribdm29 unique (answers_gift_id, answers_question_id); /;
alter table Question add constraint UK_jqsrv6dqt7ncd24m4bv3hmsdk unique (question); /;
alter table Question_Answers add constraint UK_t9v83k8xap3ur8af73j9kg683 unique (answers_gift_id, answers_question_id); /;
create sequence hibernate_sequence start with 1 increment by 1; /;
alter table Answers add constraint FKmiaeoysb01oytf17b7fwmoy3g foreign key (question_id) references Question; /;
alter table Answers add constraint FK7q93c1bi7l8kylr6ftmljvgqv foreign key (gift_id) references Gift; /;
alter table Gift_Answers add constraint FKdjqmno6wm83q5u53tn8f3phvp foreign key (answers_gift_id, answers_question_id) references Answers; /;
alter table Gift_Answers add constraint FK81hp183tk4qdnyehlch3575jf foreign key (Gift_id) references Gift; /;
alter table Question_Answers add constraint FK9dqqqjuq7uuhb8ocd051nn6sc foreign key (answers_gift_id, answers_question_id) references Answers; /;
alter table Question_Answers add constraint FKlsg0ng5pf3iamasndl4lm4dym foreign key (Question_id) references Question