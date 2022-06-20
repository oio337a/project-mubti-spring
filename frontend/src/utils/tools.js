import Header from "@editorjs/header";
import CheckList from "@editorjs/checklist";
import Table from "@editorjs/table";
import List from "@editorjs/list";
import LinkTool from "@editorjs/link";
import Image from "@editorjs/image";
import Delimiter from "@editorjs/delimiter";

export const EDITOR_JS_TOOLS = {
    // NOTE: Paragraph is default tool. Declare only when you want to change paragraph option.
    table: Table,
    list: List,
    linkTool: LinkTool,
    image: Image,
    header: Header,
    checklist: CheckList,
    delimiter: Delimiter,
}