
INSERT INTO department (id, name, faculty, head_of_department) VALUES (1, 'Математический факультет', 'Математический анализ', 'Невский М.В.');
INSERT INTO department (id, name, faculty, head_of_department) VALUES (2, 'Математический факультет', 'Дифференциальные уравнения', 'Бережной Е.И.');
INSERT INTO department (id, name, faculty, head_of_department) VALUES (3, 'Математический факультет', 'Математическое моделирование', 'Кащенко И.С.');
INSERT INTO department (id, name, faculty, head_of_department) VALUES (4, 'ИВТ', 'Математический анализ', 'Другой М.М.');
INSERT INTO department (id, name, faculty, head_of_department) VALUES (5, 'Юридический', 'Уголовное дело', 'УК Р.Ф.');
INSERT INTO department (id, name, faculty, head_of_department) VALUES (6, 'Юридический', 'Административок', 'Админ Р.Ф.');


INSERT INTO course_work (id, name, grade) VALUES (1, 'Курсовая 1', 5);
INSERT INTO course_work (id, name, grade) VALUES (2, 'Курсовая 2', 4);
INSERT INTO course_work (id, name, grade) VALUES (3, 'Курсовая 3', 4);
INSERT INTO course_work (id, name, grade) VALUES (4, 'Курсовая 4', 3);
INSERT INTO course_work (id, name, grade) VALUES (5, 'Курсовая 5', 5);
INSERT INTO course_work (id, name, grade) VALUES (6, 'Курсовая 6', 5);


INSERT INTO graduate_work (id, name, grade) VALUES (1, 'Дипломная 1', 3);
INSERT INTO graduate_work (id, name, grade) VALUES (2, 'Дипломная 2', 3);
INSERT INTO graduate_work (id, name, grade) VALUES (3, 'Дипломная 3', 4);
INSERT INTO graduate_work (id, name, grade) VALUES (4, 'Дипломная 4', 5);
INSERT INTO graduate_work (id, name, grade) VALUES (5, 'Дипломная 5', 4);
INSERT INTO graduate_work (id, name, grade) VALUES (6, 'Дипломная 6', 5);

INSERT INTO student (id, full_name, stud_number, department_id, started_at, education_level, course_work_id, graduate_work_id, end_at, successfully_graduate, deleted)
VALUES (1, 'Романов Вячеслав Дмитриевич', '01012023', 1, '2019-09-01 00:00:00.000000', 'бакалавр', 1, 1, '2023-07-12 00:00:00.000000', true, false);
INSERT INTO student (id, full_name, stud_number, department_id, started_at, education_level, course_work_id, graduate_work_id, end_at, successfully_graduate, deleted)
VALUES (2, 'Сончилеев Егор Валерьевич', '02012023', 2, '2019-09-01 00:00:00.000000', 'бакалавр', 2, 3, '2023-07-12 00:00:00.000000', true, false);
INSERT INTO student (id, full_name, stud_number, department_id, started_at, education_level, course_work_id, graduate_work_id, end_at, successfully_graduate, deleted)
VALUES (3, 'Лузин Илья Сергеевич', '03012023', 1, '2019-09-01 00:00:00.000000', 'бакалавр', 3, 2, '2023-07-12 00:00:00.000000', true, false);
INSERT INTO student (id, full_name, stud_number, department_id, started_at, education_level, course_work_id, graduate_work_id, end_at, successfully_graduate, deleted)
VALUES (4, 'Иванов Иван Иванович', '04012023', 4, '2019-09-01 00:00:00.000000', 'бакалавр', 4, 4, '2023-07-12 00:00:00.000000', true, true);
INSERT INTO student (id, full_name, stud_number, department_id, started_at, education_level, course_work_id, graduate_work_id, end_at, successfully_graduate, deleted)
VALUES (5, 'Иванов Антон Геннадьевич', '05012023', 5, '2018-09-01 00:00:00.000000', 'бакалавр', null, null, '2020-08-04 00:00:00.000000', false, false);
INSERT INTO student (id, full_name, stud_number, department_id, started_at, education_level, course_work_id, graduate_work_id, end_at, successfully_graduate, deleted)
VALUES (6, 'Кузин Евгений Андреевич', '06012023', 5, '2019-10-01 00:00:00.000000', 'бакалавр', 5, null, '2022-01-10 00:00:00.000000', false, true);
