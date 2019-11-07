lunch_menu = {
    "20_floor" : {
        "A" : "돈까스",
        "B" : "순댓국"
    },
    
    "양자강" : {
        "점심메뉴" : "탕짬면",
        "저녁메뉴" : "군만두"
    },

    "대동집" : {
        "밥안주" : "비빔면",
        "술안주" : "오돌뼈"
    }
}

lunch_menu["경성불백"] = {
    "한식메뉴" : "석쇠불고기",
    "특식메뉴" : "돈까스"
}

for menu in lunch_menu.keys():
    print(menu)

for menu_detail in lunch_menu.values():
    print(menu_detail)

for key, value in lunch_menu.items():
    print(key)
    print(value)

