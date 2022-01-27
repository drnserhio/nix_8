
export abstract class ResponseTablePage<T> {

  content: T[];
  page: number;
  totalPages: number
  showEntity: number;
  allSizeEntity: number;
  sort: string;
  showEntityTo: number
  showEntityFrom: number;

}
