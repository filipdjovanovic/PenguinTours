import React ,{ useEffect,useState} from "react";
import Cardlist from "./cardlist";

import ReactPaginate from "https://cdn.skypack.dev/react-paginate@7.1.3";

const items = [...Array(500).keys()];

function Items({ currentItems }) {
  return (
    <div className="container my-2" style={{border: 'solid',borderColor:'navy',borderRadius:'20px'}}>
            <div className="row row-cols-1 row-cols-md-3 g-4 my-2 justify-content-center">
    {currentItems && currentItems.map((item) => (
        <Cardlist name='Rim novembar 2022' destinaction='Rim' days='3' price='105' transport='Autobus' />
    ))}
      </div>
      </div>
  );
}

export default function PaginatedItems({ itemsPerPage }) {
  // We start with an empty list of items.
  const [currentItems, setCurrentItems] = useState(null);
  const [pageCount, setPageCount] = useState(0);
  // Here we use item offsets; we could also use page offsets
  // following the API or data you're working with.
  const [itemOffset, setItemOffset] = useState(0);

  useEffect(() => {
    // Fetch items from another resources.
    const endOffset = itemOffset + itemsPerPage;
    console.log(`Loading items from ${itemOffset} to ${endOffset}`);
    setCurrentItems(items.slice(itemOffset, endOffset));
    setPageCount(Math.ceil(items.length / itemsPerPage));
  }, [itemOffset, itemsPerPage]);

  // Invoke when user click to request another page.
  const handlePageClick = (event) => {
    const newOffset = event.selected * itemsPerPage % items.length;
    console.log(`User requested page number ${event.selected}, which is offset ${newOffset}`);
    setItemOffset(newOffset);
  };

  return (
    <>
      <Items currentItems={currentItems} />
      <div className="container">
      <div className="row justify-content-center">
      <ReactPaginate
        nextLabel="next >"
        onPageChange={handlePageClick}
        pageRangeDisplayed={3}
        marginPagesDisplayed={2}
        pageCount={pageCount}
        previousLabel="< previous"
        pageClassName="page-item"
        pageLinkClassName="page-link"
        previousClassName="page-item"
        previousLinkClassName="page-link"
        nextClassName="page-item"
        nextLinkClassName="page-link"
        breakLabel="..."
        breakClassName="page-item"
        breakLinkClassName="page-link"
        containerClassName="pagination"
        activeClassName="active"
        renderOnZeroPageCount={null}
        
      />
      </div>
      </div>
    </>
  );
}
