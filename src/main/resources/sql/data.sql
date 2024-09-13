insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000001, '2023-05-12', 01,
               '2023-05-12', 'Email', 'ЦБ',
               'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
                   true, false, '/uploads/0000001/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000002, '2024-06-12', 02,
        '2024-09-09', 'Email', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000002/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000003, '2024-07-12', 03,
        '2024-09-09', 'Email', 'ГНИ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000003/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000004, '2024-08-12', 04,
        '2024-09-09', 'Email', 'ТСЖ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000004/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000005, CURRENT_DATE, 05,
        CURRENT_DATE, 'Курьер', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000005/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000006, CURRENT_DATE, 06,
        CURRENT_DATE, 'Email', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000006/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000007, CURRENT_DATE, 07,
        CURRENT_DATE, 'Телефонограмма', 'ЦБ',
        'Важная тестовая тема документа.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000007/test.pdf');

-- Документы за 1 квартал, не из ГНИ и не с доставкой курьером (эти должны быть включены в выборку)
INSERT INTO incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
VALUES
    (0000008, '2024-01-15', 08,
     '2024-01-16', 'Email', 'ЦБ',
     'Важное сообщение', 'Описание документа', '2024-01-20',
     TRUE, FALSE, '/uploads/0000008/test.pdf'),
    (0000009, '2024-02-10', 09,
     '2024-02-11', 'Телефонограмма', 'ТСЖ',
     'Уведомление', 'Описание документа', '2024-02-15',
     FALSE, TRUE, '/uploads/0000009/test.pdf'),
    (0000010, '2024-03-20', 10,
     '2024-03-21', 'Email', 'ЦБ',
     'Запрос информации', 'Описание документа', '2024-03-25',
     TRUE, TRUE, '/uploads/00000010/test.pdf');

-- Документы за 1 квартал, поступившие из ГНИ с доставкой курьером (эти должны быть исключены из выборки)
INSERT INTO incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
VALUES
    (0000011, '2024-01-25', 11,
     '2024-01-26', 'Курьер', 'ГНИ',
     'Налоговое уведомление', 'Описание документа', '2024-01-30',
     FALSE, FALSE, '/uploads/00000011/test.pdf'),
    (0000012, '2024-02-15', 12,
     '2024-02-16', 'Курьер', 'ГНИ',
     'Требование уплаты', 'Описание документа', '2024-02-20',
     TRUE, TRUE, '/uploads/00000012/test.pdf'),
    (0000013, '2024-03-10', 13,
     '2024-03-11', 'Курьер', 'ГНИ',
     'Документ о проверке', 'Описание документа', '2024-03-15',
     FALSE, TRUE, '/uploads/00000013/test.pdf');

-- Документы за 1 квартал, поступившие из ГНИ с другими способами доставки (эти должны быть включены в выборку)
INSERT INTO incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
VALUES
    (0000014, '2024-01-30', 14,
     '2024-01-31', 'Email', 'ГНИ',
     'Отчетность', 'Описание документа', '2024-02-05',
     TRUE, FALSE, '/uploads/00000014/test.pdf'),
    (0000015, '2024-02-28', 15,
     '2024-02-29', 'Телефонограмма', 'ГНИ',
     'Информационное письмо', 'Описание документа', '2024-03-05',
     FALSE, TRUE, '/uploads/00000015/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000016, date_trunc('day', NOW() - interval '1 month'), 16,
        CURRENT_DATE, 'Телефонограмма', 'ЦБ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000016/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000017, date_trunc('day', NOW() - interval '1 month'), 17,
        CURRENT_DATE, 'Email', 'ТСЖ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000017/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000018, date_trunc('day', NOW() - interval '1 month'), 18,
        CURRENT_DATE, 'Телефонограмма', 'ТСЖ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000018/test.pdf');


insert into incoming_document (reg_number, reg_date, out_doc_number,
                               out_doc_date, delivery_method, correspondent,
                               subject, description, due_date,
                               access, control, file_upload)
values (0000019, date_trunc('day', NOW() - interval '2 month'), 19,
        CURRENT_DATE, 'Телефонограмма', 'ТСЖ',
        'Важная тестовая тема документа, кредит.', 'Подробное тестовое описание документа.', '2024-09-15',
        true, false, '/uploads/0000019/test.pdf');
