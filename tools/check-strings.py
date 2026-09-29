from pathlib import Path
import sys
import xml.etree.ElementTree as ET


PROJECT_DIR = Path(__file__).resolve().parent.parent
RESOURCES_DIR = PROJECT_DIR / "shared" / "src" / "commonMain" / "composeResources"
RUSSIAN_FILE = RESOURCES_DIR / "values" / "strings.xml"
ENGLISH_FILE = RESOURCES_DIR / "values-en" / "strings.xml"


def string_names(path: Path) -> set[str]:
    root = ET.parse(path).getroot()
    return {item.attrib["name"] for item in root.findall("string")}


russian_names = string_names(RUSSIAN_FILE)
english_names = string_names(ENGLISH_FILE)

missing_in_english = russian_names - english_names
missing_in_russian = english_names - russian_names

if missing_in_english or missing_in_russian:
    print("String resource keys do not match.")
    print("Missing in values-en:", sorted(missing_in_english))
    print("Missing in values:", sorted(missing_in_russian))
    sys.exit(1)

print(f"OK: both locales contain {len(russian_names)} matching string keys.")
