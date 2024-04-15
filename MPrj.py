def save_fine(fine, fine_item, name):
    with open("fines.txt", "a") as file:
        file.write(f"{name.strip()}, {fine_item.strip()}, {fine.strip()}\n")
    print("과태료 정보가 저장되었습니다.")

def search_fine(name, fine_item=None):
    try:
        with open("fines.txt", "r") as file:
            found = False
            for line in file:
                parts = line.split(",")
                if name.lower() in parts[0].lower():
                    print(f"이름: {parts[0].strip()}, 종목: {parts[1].strip()}, 과태료: {parts[2].strip()}")
                    found = True
            if not found:
                print("해당하는 과태료 정보를 찾을 수 없습니다.")
    except FileNotFoundError:
        print("저장된 과태료 정보가 없습니다.")

def show_menu():
    print("\n[메뉴]")
    print("1. 입력")
    print("2. 조회")
    print("3. 종료")

# 메뉴 기능 실행
while True:
    show_menu()
    choice = input("메뉴를 선택하세요: ")

    if choice == "1":
        name = input("이름을 입력하세요: ")
        fine_item = input("과태료의 종목을 입력하세요: ")
        fine_amount = input("과태료를 입력하세요: ")
        save_fine(fine_amount, fine_item, name)
    elif choice == "2":
        search_name = input("조회할 이름을 입력하세요: ")
        search_item = input("조회할 과태료 종목을 입력하세요 (입력하지 않으면 모든 과태료를 조회합니다): ")
        if search_item:
            search_fine(search_name, search_item)
        else:
            search_fine(search_name)
    elif choice == "3":
        print("프로그램을 종료합니다.")
        break
    else:
        print("올바른 메뉴를 선택하세요.")

