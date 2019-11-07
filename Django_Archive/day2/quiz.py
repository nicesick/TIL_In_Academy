score = {
    "수학" : 90,
    "영어" : 87,
    "한국지리" : 92
}

sum = 0

for num in score.values():
    sum += num

avg = sum / len(score)
print(round(avg, 1))

scores = {
    "a학생" : {
        "수학" : 80,
        "국어" : 90,
        "음악" : 100
    },

    "b학생" : {
        "수학" : 90,
        "국어" : 90,
        "음악" : 60
    }
}

sum_students = []
avg_students = []
index = 0

for student in scores.values():
    sum_students.append(0)
    avg_students.append(0)

    for lecture in student.values():
        sum_students[index] += lecture
    
    avg_students[index] = sum_students[index] / len(student.values())
    index += 1

total_sum = 0

for avg in avg_students:
    print(round(avg,1))
    total_sum += avg

total_avg = total_sum / len(avg_students)
print(round(total_avg,1))