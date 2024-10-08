import BookPreview from '../components/BookPreview';
import bookResource from '../resources/BookResource'; 
import { useEffect, useState } from 'react';
import BookFilter from '../components/BookFilter';

/**
 * Home Component
 * 
 * This component serves as the main page for displaying a list of books. It fetches and displays book data from `bookResource`, with the ability to filter and sort the books using the `BookFilter` component. The `BookPreview` component is used to render each individual book.
 * 
 * Features:
 * - Fetches and displays a list of books using the `bookResource.findAll` method.
 * - Provides filtering and sorting options via the `BookFilter` component.
 * - Manages component state for the book data, page request parameters (including sort and filter), and loading/error states using React hooks (`useState` and `useEffect`).
 * - Renders a grid of book previews with the `BookPreview` component.
 * 
 * Usage:
 * ```jsx
 * import Home from './Home';
 * 
 * function App() {
 *   return (
 *     <div>
 *       <Home />
 *     </div>
 *   );
 * }
 * ```
 * 
 * @component
 * @example
 * // Home component displays a list of books with filtering and sorting options
 * <Home />
 */
export default function Home() {

    const [data, setData] = useState([]);
    const [pageRequest, setPageRequest] = useState({sort:'price'});
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await bookResource.findAll(pageRequest);
                setData(result);
                setIsLoading(false);
            } catch (err) {
                setError(err);
                setIsLoading(false);
            }
        };

        fetchData();
    }, [pageRequest]);

    const adjustFilter = (filter) => {
        setPageRequest({...pageRequest, filter});
    }

    const adjustSort = (sort) => {
        setPageRequest({...pageRequest, sort});
    }


    return (
        <div className="mx-auto max-w-3xl px-4 sm:px-6 lg:max-w-7xl lg:px-8">

            <BookFilter pageRequest={pageRequest} onFilterChange={adjustFilter} onSortChange={adjustSort}></BookFilter>

            {/* Product grid */}
            <section aria-labelledby="products-heading" className="mt-8 pb-24">
                <h2 id="products-heading" className="sr-only">
                    Books
                </h2>

                <div className="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:gap-x-8">
                    {data.map((book) => (
                        <BookPreview key={book.id} book={book}></BookPreview>
                    ))}
                </div>
            </section>


        </div>
    );
}