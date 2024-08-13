/**
 * DataListItem Component
 * 
 * This component represents an individual item within a `DataList`. It provides a flexible layout
 * for displaying content on the left (children) and an optional suffix on the right, such as
 * additional information or actions.
 * 
 * Props:
 * - `children` (ReactNode): The main content of the list item, typically rendered on the left side.
 * - `suffix` (ReactNode): Optional. Additional content rendered on the right side, such as icons, buttons, or status indicators.
 * 
 * Usage:
 * ```jsx
 * <DataListItem suffix={<span>Extra Info</span>}>
 *   <span>Main Content</span>
 * </DataListItem>
 * ```
 * 
 * Internal Layout:
 * - The `children` are placed inside a `div` with `min-w-0` to ensure proper text truncation and prevent overflow.
 * - If the `suffix` prop is provided, it is rendered inside a `div` with `flex shrink-0 items-center gap-x-4` to align it with the content on the left while preventing it from shrinking.
 * 
 * @component
 * @example
 * <DataListItem suffix={<button>Action</button>}>
 *   <div>Item 1</div>
 * </DataListItem>
 */
export default function DataListItem({ suffix, children }) {

    return (
        <li className="relative flex justify-between gap-x-6 px-4 py-5 hover:bg-gray-50 sm:px-6">
            <div className="flex min-w-0 gap-x-4">
                {children}
            </div>
            {!!suffix &&
                <div className="flex shrink-0 items-center gap-x-4">

                    {suffix}
                </div>
            }
        </li>
    )
}