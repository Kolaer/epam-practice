CREATE TRIGGER giftTrigger
  AFTER INSERT
  ON Gift
  REFERENCING NEW ROW AS newrow
  FOR EACH ROW
  BEGIN ATOMIC
    FOR SELECT *
        FROM QUESTION DO
      INSERT INTO Answers VALUES (1, 1, 1, id, newrow.id);
    END FOR;
  END;
/;

CREATE TRIGGER questionTrigger
  AFTER INSERT
  ON Question
  REFERENCING NEW ROW AS newrow
  FOR EACH ROW
  BEGIN ATOMIC
    FOR SELECT *
        FROM Gift DO
      INSERT INTO Answers VALUES (1, 1, 1, newrow.id, id);
    END FOR;
  END;
/;